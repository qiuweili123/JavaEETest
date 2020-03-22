--[[
-- 兼容http://pic.secooimg.com/thumb/112/112/pic1.secoo.com/  前缀访问的图片
-- TODO 使用的旧功能切割的图片，后使用新的功能实现动态，该功能兼容就得访问链接
-- ]]
local file_util = require 'common.util.file'

local ngx_log = ngx.log
local ngx_debug = ngx.DEBUG
local ngx_var = ngx.var
local ngx_exec = ngx.exec
local ngx_exit = ngx.exit

-- 图片存储根路径
local store_path_prefix = ngx_var.root_path
local request_uri = ngx_var.request_uri

-- 允许处理的尺寸
local allow_size_config = {
    "345/385",
	"109/73",
	"160/94",
	"180/240",
	"395/232",
	"40/40",
	"44/23",
	"58/78",
	"60/60",
	"790/464",
	"112/112",
	"50/50",
	"120/120"
}
local size_config = {
    "345/385",
	"109x73",
	"160x94",
	"180x240",
	"395x232",
	"40x40",
	"44x23",
	"58x78",
	"60x60",
	"790x464",
	"112x112",
	"50x50",
	"120x120"
}
-- 是否允许的尺寸
local function is_allow_size()
	for k,v in pairs(allow_size_config) do
		local _index=string.find(request_uri,v)
		if _index and _index>0 then
           return size_config[k]
		end
	end
end

local index = string.find(request_uri, 'thumb/%d+/%d+/pic1.secoo.com/')
if index and index > 0 then
	local size = is_allow_size()
	if size then
		local new_uri = string.gsub(request_uri, '/thumb/%d+/%d+/pic1.secoo.com', '')
		if file_util.exists(store_path_prefix .. '/' .. new_uri) then
			local new_url = '/' .. new_uri .. '_!!'..size..'c3.jpg'
			ngx.log(ngx_debug, "新url=", new_url)
			return ngx_exec(new_url)
		else
			ngx.exit(404)
		end
	else
		ngx.exit(404)
	end
end
ngx.exit(404)