local ngx_var = ngx.var
local ngx_log = ngx.log
local debug = ngx.DEBUG

function parseUri(uri)

    local _, _, name, ext, size = string.find(uri, "(.+)(%..+)!(%d+x%d+)")

    if name and size and ext then
        return ngx.var.image_root .. name .. ext, size
    else
        return "",""
    end

end

local function resize()
    local ori_filename, size = parseUri(ngx_var.uri)
    -- ngx_log(debug,);
    --ngx.print(ori_filename)
   -- ngx_log(debug,ori_filename)
end


  function test()
    ngx.print(package.path)
    -- ngx.say("ytest rerite" .. ngx_var.project_name);
end


test()