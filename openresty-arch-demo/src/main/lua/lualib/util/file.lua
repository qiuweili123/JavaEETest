local os = os
local io = io
-- local str_match = string.match
local str_match = ngx.re.match

local _M = {
    _VERSION = '0.01',
}
local mt = { __index = _M }

-- 检测路径是否目录
function _M.is_dir(path)
   return (_M.exists(name) and not _M.isFile(name))
end

 
-- 是否是文件
function _M.is_file(name)
    if type(name)~="string" then return false end
    if not _M.exists(name) then return false end
    local f = io.open(name)
    if f then
        f:close()
        return true
    end
    return false
end

-- 文件是否存在
function _M.exists(name)
    if type(name)~="string" then return false end
    return os.rename(name,name) and true or false
end

-- 获取文件路径
function _M.get_file_dir(filename)
    -- return str_match(filename, "(.+)/[^/]*%.%w+$")
    -- print(filename)
    local m = str_match(filename, [=[\s*(/?(?:[^/]*?/)+)[^/]*$]=])
    if m and m[1] then
        return m[1]
    end
    return nil
end

-- 获取文件所在目录
function _M.get_file_curr_dir(filename)
    local m = str_match(filename, [=[^/(?:[^/]*?/)*]=])
    if m and m[0] then
        return m[0]
    end
    return nil
end

-- 创建目录
function _M.create_dir(path) 
    -- print("create_dir:"..path)
    if not _M.is_dir(path) then
        os.execute("mkdir -p " .. path )
    end
end

-- 移动目录
function _M.move(oldname, newname)
    os.execute("mv ".. oldname .. ' ' .. newname)
end

-- 删除文件
function _M.remove(filename)
    os.remove(filename)
end



--获取文件扩展名
function _M.ext_by_content_type(content_type)
	local ext = ''
    if content_type == 'image/png' then
        ext = 'png'
    elseif content_type == 'image/jpg' or content_type == 'image/jpeg' then
        ext = 'jpg'
    elseif content_type == 'image/gif' then
        ext = 'gif'
    end
   	return ext
end

--获取文件扩展名
function _M.ext_by_file_name(file_name)
	return file_name:match(".+%.(%w+)$")
end


return _M
