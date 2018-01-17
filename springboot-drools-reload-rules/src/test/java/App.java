import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

/**
 * 端通过kie-ci来动态从maven repo中获取指定 rules jar版,会与mavaen有一定的耦合
 */
public class App {
    public static void main(String[] args) throws Exception {
        ReleaseIdImpl releaseId = new ReleaseIdImpl("com.test", "driving", "LATEST");
        KieServices ks = KieServices.Factory.get();
        KieContainer container = ks.newKieContainer(releaseId);
        KieScanner scanner = ks.newKieScanner(container);
        scanner.start(1000);
        StatelessKieSession session = container.newStatelessKieSession();

        for (int i = 0; i < 100; i++) {
            FactType factType = factType(container.getKieBase());
            Object applicant = makeApplicant(factType);
            session.execute(applicant);
            System.out.println("申请人：" + factType.get(applicant, "name") + "，年龄：" + factType.get(applicant, "age") + "是否可以申请驾照" + factType.get(applicant, "valid"));

            Thread.sleep(20000);//休眠20秒，等待更新规则查看输出结果  
        }
    }

    private static Object makeApplicant(FactType factType) throws Exception {
        Object applicant = factType.newInstance();
        factType.set(applicant, "name", "张三");
        factType.set(applicant, "age", 17);
        return applicant;
    }

    protected static FactType factType(KieBase base) {
        FactType factType = base.getFactType("com.test.driving", "Applicant");
        return factType;
    }
}  