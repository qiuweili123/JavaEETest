local json = require "cjson"


local ngx_capture = ngx.location.capture
local ngx_exec = ngx.exec
local ngx_log = ngx.log
local ngx_debug = ngx.DEBUG


local _M = {
    _VERSION = '0.01',
    _PREFIX_ACT = 'imageView2',
    _PREFIX_THUMB = 'imageMogr2/thumbnail'
}

local mt = { __index = _M }


-- 去除图片信息，原图返回
_M["00"] = function()
    return _M._PREFIX_ACT;
end

-- 缺省操作，限定缩略图的长边和短边的最小值分，进行等比压缩
_M["c0"] = function(height, width)
    return _M._PREFIX_ACT .. "/4/w/" .. width .. "/h/" .. height;
end
-- 限定缩略图的宽高最小值。该操作会将图像等比缩放直至某一边达到设定最小值，之后将另一边居中裁剪至设定值.
_M["c1"] = function(height, width)
    return _M._PREFIX_ACT .. "/1/w/" .. width .. "/h/" .. height;
end
--等比压缩，居中裁剪
_M["c2"] = function(height, width)
    return _M._PREFIX_ACT .. "/5/w/" .. width .. "/h/" .. height;
end

--按照最长边等比缩放
_M["c3"] = function(height, width)
    return _M._PREFIX_ACT .. "/2/w/" .. width .. "/h/" .. height;
end
--按照最短边等比缩放
_M["c4"] = function(height, width)
    return _M._PREFIX_ACT .. "/3/w/" .. width .. "/h/" .. height;
end
_M["c5"] = function(height, width, path)
    ngx_log(ngx_debug, "path::",ngx.var.tx_cos_host);
    --200x200 处理特殊情况
    if height == width then
        return _M._PREFIX_THUMB .. "/" .. width .. "x|" .. _M._PREFIX_ACT .. "/1/w/" .. width
    end


    --1. 同时大于0，原图为500x200
   --[[ if height > 0 and width > 0 then
        local img_path = path .. "?imageInfo";
        local res = ngx_capture("/image-info", {
            vars = {
                my_uri = img_path
            }
        });

        ngx_log(ngx_debug, "res::", res.status);

        local img_org_width;
        local img_org_height;
        if res == nil and res.status == ngx.HTTP_OK then
            ngx_log(ngx_debug, "res.body::", res.body);
            local img_org_info = json.decode(res.body);
            img_org_width = tonumber(img_org_info["width"]);
            img_org_height = tonumber(img_org_info["height"]);
        end
        --存在原图并且获取图片的原始尺寸大小
        -- 如果原始图片小于需求图片尺寸，如800x100/100x300/800x500/500x800,需要进一步讨论此处的4种情况
        ngx_log(ngx_debug, "img_org_info width::", img_org_width, "height::", img_org_height);
        if img_org_width ~= nil and img_org_height ~= nil and (img_org_height < img_height or img_org_width < img_width) then
            --高宽相等或者宽大于高，需放大宽（采用最大值放大），否则放大宽,使用管道方式进行操作
            if img_height == img_width or img_height < img_width then
                return M._PREFIX_THUMB .. "/" .. width .. "x|"
            else
                table_util.apend(t_uri_args, "thumbnail", "x" .. img_height, "|");
            end
        else
            -- 4.显示图片尺寸同时比原图尺寸小，如20x30,安装最短边，缩放后裁剪不出现白框，但有可能减掉多余图片
            return _M._PREFIX_ACT .. "/1/w/" .. width .. "/h/" .. height;
        end

    end]]


    --以下为1边为0的情况
    local act_url;
    --200x0
    if height == 0 then
        act_url = _M._PREFIX_THUMB .. "/" .. width .. "x|"
    end
    --0x200
    if width == 0 then
        act_url = _M._PREFIX_THUMB .. "/x" .. height .. "|"
    end

    return act_url .. _M._PREFIX_ACT;
end

return _M;