local file_util = require 'common.util.file'

local ngx_log = ngx.log
local ngx_debug = ngx.DEBUG
local ngx_var = ngx.var
local ngx_exec = ngx.exec
local ngx_exit = ngx.exit


-- 允许处理的尺寸
local allow_size_config = {
	"50/50",
	"65/65",
	"80/80",
	"120/120",
	"160/160",
	"200/200",
	"240/240",
	"300/300",
	"317/317",
	"345/385",
	"345/460",
	"374/374",
	"500/500",
	"690/300",
	"700/700",
	"750/750",
	"750/920"
}


-- 图片存储根路径
local store_path_prefix = ngx_var.root_path
-- 图片存储根路径（旧目录）
local store_path_prefix_old = ngx_var.root_path_old
-- 图片宽度
local img_width = ngx_var.img_width
-- 图片高度
local img_height = ngx_var.img_height
-- 图片地址后缀
local img_path_suffix = ngx_var.img_path_suffix


local size_str = img_width .. '/' .. img_height

-- 是否允许的尺寸
local function is_allow_size()
	for k,v in pairs(allow_size_config) do
		if size_str == v then
			return true
		end
	end
	return false
end


-- 校验尺寸
local is_allow_size = is_allow_size()
if not is_allow_size then
	return ngx_exit(404)
end

-- 图片原图地址
-- 20181023 商品图片上传路径修改以后图片可能存在新的路径里边 内部重定向到新连接
local img_src_path = store_path_prefix .. '/' .. img_path_suffix
-- 校验图片是否是新地址的图片
ngx_log(ngx_debug,"新地址 img_src_path:",img_src_path)
if file_util.exists(img_src_path) then
	local new_url='/'..img_path_suffix..'_!!'..img_width..'x'..img_height..'.jpg'
	ngx_log(ngx_debug,"新地址:",new_url)
	return  ngx.exec(new_url)
end


-- 图片原图地址
img_src_path = store_path_prefix .. '/product/yt/' .. img_path_suffix
-- 图片原图地址（旧目录）
local img_src_path_old = store_path_prefix_old .. '/product/yt/' .. img_path_suffix
ngx_log(ngx_debug," 原图:",img_src_path)



-- 原图不存在
if  file_util.exists(img_src_path) then ngx_log(ngx_debug,'原图在新目录里边',img_src_path)
else
	if  file_util.exists(img_src_path_old) then  img_src_path=img_src_path_old ngx_log(ngx_debug,'原图在旧目录里边',img_src_path)
	else
		ngx_log(ngx_debug,'原图不存在',img_src_path)
		return ngx_exit(404)
	end
end

-- 继续访问文件
return ngx_exec("@dynamic_process")