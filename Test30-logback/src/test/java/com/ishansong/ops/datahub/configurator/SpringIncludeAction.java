package com.ishansong.ops.datahub.configurator;

import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.event.SaxEvent;
import ch.qos.logback.core.joran.event.SaxEventRecorder;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.xml.sax.Attributes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SpringIncludeAction extends Action {
    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
    private static final String classPath = "classpath*:";
    private static final String INCLUDED_TAG = "included";
    private static final String RESOURCES_ATTR = "resources";
    private static final String OPTIONAL_ATTR = "optional";


    private boolean optional;

    @SneakyThrows
    @Override
    public void begin(InterpretationContext ec, String name, Attributes attributes) throws ActionException {
        this.optional = OptionHelper.toBoolean(attributes.getValue(OPTIONAL_ATTR), false);

        if (!checkAttributes(attributes)) {
            return;
        }

        String resPath = attributes.getValue(RESOURCES_ATTR);
        resPath = resPath.contains(classPath) ? resPath : classPath + resPath;

        Resource[] resources = resourceResolver.getResources(resPath);

        InputStream in =null;
        SaxEventRecorder recorder = null;
        List<SaxEvent> allSaxEventList= new ArrayList<SaxEvent>();

        for(Resource resource:resources){
            URL url = resource.getURL();
            ConfigurationWatchListUtil.addToWatchList(context, url);

            in=resource.getInputStream();
            recorder= new SaxEventRecorder(context);
            try {
                if (in != null) {
                    parseAndRecord(in, recorder);
                    // remove the <included> tag from the beginning and </included> from the end
                    trimHeadAndTail(recorder);
                    allSaxEventList.addAll(recorder.saxEventList);

                }
            } catch (JoranException e) {
                addError("Error while parsing  ", e);
            } finally {
                close(in);
            }
        }
        // offset = 2, 需要跳过自身的位置，同时需要跳过/> 结束位置,然后进行past
        ec.getJoranInterpreter().getEventPlayer().addEventsDynamically(allSaxEventList, 2);
    }

    void close(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
            }
        }
    }

    private boolean checkAttributes(Attributes attributes) {
        String resourceAttribute = attributes.getValue(RESOURCES_ATTR);
        int count = 0;
        if (!OptionHelper.isEmpty(resourceAttribute)) {
            count++;
        }
        if (count == 0) {
            addError("One of \"path\", \"resource\" or \"url\" attributes must be set.");
            return false;
        } else if (count > 1) {
            addError("Only one of \"data\", \"url\" or \"resource\" attributes should be set.");
            return false;
        } else if (count == 1) {
            return true;
        }
        throw new IllegalStateException("Count value [" + count + "] is not expected");
    }

    private void trimHeadAndTail(SaxEventRecorder recorder) {
        List<SaxEvent> saxEventList = recorder.saxEventList;

        if (saxEventList.size() == 0) {
            return;
        }
        SaxEvent first = saxEventList.get(0);
        if (first != null && first.qName.equalsIgnoreCase(INCLUDED_TAG)) {
            saxEventList.remove(0);
        }

        SaxEvent last = saxEventList.get(recorder.saxEventList.size() - 1);
        if (last != null && last.qName.equalsIgnoreCase(INCLUDED_TAG)) {
            saxEventList.remove(recorder.saxEventList.size() - 1);
        }
    }

    private void parseAndRecord(InputStream inputSource, SaxEventRecorder recorder) throws JoranException {
        recorder.setContext(context);
        recorder.recordEvents(inputSource);
    }

    @Override
    public void end(InterpretationContext ec, String name) throws ActionException {
        // do nothing
    }

}
