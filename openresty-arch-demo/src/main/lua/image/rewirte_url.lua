local ngx_var = ngx.var
local ngx_log = ngx.log
local debug = ngx.DEBUG

local function parseUri(uri)

    local _, _, name, ext, size = string.find(uri, "(.+)(%..+)!(%d+x%d+)")

    if name and size and ext then
        return ngx.var.image_root .. name .. ext, size
    else
        return "", ""
    end

end

local function resize()
    local ori_filename, size = parseUri(ngx_var.uri)
    -- ngx_log(debug,);
    --ngx.print(ori_filename)
    -- ngx_log(debug,ori_filename)

    -- print_data("0","sdsdsd");
end

local function print_data(code, data)
    local responseBody = ngx.location.capture("/httpStatusRewrite", { method = ngx.Http_POST, body = "测试", args = { apiRespCodeParam = code } });

    ngx.exit(ngx.HTTP_OK)
end

local responseBody = ngx.location.capture("/httpStatusRewrite", { method = ngx.Http_POST, body = "测试", args = { apiRespCodeParam = 0 } });
ngx.print(responseBody.body);
ngx.exit(ngx.HTTP_OK);
-- ngx_log(debug,"this is responseBody :",responseBody.body);
-- ngx_log.log(debug,"package.path",package.path);
--- test()
-- resize();