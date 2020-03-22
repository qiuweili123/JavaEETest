local file_util = require 'util.file'

local ngx_log = ngx.log
local ngx_debug = ngx.DEBUG
local ngx_var = ngx.var
local ngx_exec = ngx.exec
local ngx_exit = ngx.exit


-- 允许处理的尺寸
local allow_size_config = {
    "0/0",
    "638/638",
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
    "800/600",
    "800/800",
    "638/420",
    "650/650",
    "200/200",
    "20/20",
    "750/750",
    "50/50",
    "65/65",
    "80/80",
    "120/120",
    "160/160",
    "240/240",
    "300/300",
    "374/374",
    "317/317",
    "345/460",
    "345/385",
    "500/500",
    "690/300",
    "700/700",
    "750/920"
}
-- 允许的后缀
local allow_suffix_config = {"jpg","jpeg","png","webp","JPG","JPEG","PNG"}
-- 长方形压缩压缩不同比例的参数
local allow_crop_config = {"c1","c2","c3","c4"}

-- 图片存储根路径
local store_path_prefix = ngx_var.root_path

-- 图片目录
local img_place =ngx_var.img_place;
-- 图片名称
local img_name = ngx_var.img_name;
-- 图片宽度
local img_width = ngx_var.img_width
-- 图片高度
local img_height = ngx_var.img_height
-- 图片质量
local img_quality = ngx_var.img_quality
-- 转换图片格式
local img_suffix = ngx_var.img_suffix
-- 长方形图片切割
local img_crop = ngx_var.img_crop
ngx.log(ngx_debug,"参数=",'img_place=',img_place,'；img_name=',img_name,'；img_width=',img_width,'；img_height=',img_height,'；img_quality=',img_quality,'；img_suffix=',img_suffix,"，img_crop=",img_crop)


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
-- 方形压缩压缩不同比例的参数
local function is_crop_size()
    for k,v in pairs(allow_crop_config) do
        if img_crop == v then
            return true
        end
    end
    return false
end

-- 允许的格式
local function is_allow_suffix()
    for k,v in pairs(allow_suffix_config) do
        if img_suffix == v then
            return true
        end
    end
    return false
end
-- 校验尺寸
local is_allow_size = is_allow_size()
if not is_allow_size then
    ngx_log(ngx_debug," 尺寸不允许",size_str)
    return ngx_exit(404)
end

if img_crop and img_crop~='' then
    local is_crop_size = is_crop_size()
    if not is_crop_size then
        ngx_log(ngx_debug, "长方形压缩参数不对", img_crop)
        return ngx_exit(404)
    end
end

-- 校验后缀
local is_allow_suffix = is_allow_suffix()
if not is_allow_suffix then
    ngx_log(ngx_debug," 后缀不允许",img_suffix)
    return ngx_exit(404)
end
-- 图片质量不能为0
img_quality=tonumber(img_quality)
if  img_quality and (type(img_quality)~='number' or  100<img_quality or 0>=img_quality )  then
    ngx_log(ngx_debug,"img_quality不对：",img_quality)
    return ngx_exit(404)
end
-- 切割图片地址
local img_src_path=store_path_prefix .. '/' .. img_place .. '/'.. img_name
ngx_log(ngx_debug,"需要切割图片地址：",img_src_path)
--[[
   -- 兼容http://pic.secooimg.com/thumb/${width}/${hei}/pic1.secoo.com/  前缀访问的图片
   -- TODO 使用的旧功能产生的缩略图，后使用新的功能实现动态，该功能兼容旧的访问链接
   -- ]]

local index= string.find(img_place,'thumb/%d+/%d+/pic1.secoo.com/')
--       ngx_log(ngx_debug,"index：",index,'，img_src_path=',img_src_path)
if index and index > 0 then
    local temp_img_place = string.gsub(img_place, 'thumb/%d+/%d+/pic1.secoo.com/', '')
    local temp_img_src_path =store_path_prefix .. '/' .. temp_img_place .. '/' .. img_name
    ngx_log(ngx_debug,"temp_img_src_path：",temp_img_src_path)
    if file_util.exists(temp_img_src_path) then
        img_src_path = temp_img_src_path
    end
end

-- 判断类似http://pic12.secooimg.com/product/${width}/${hei}/res/XXXXX 格式的图片
local index= string.find(img_place,'product/%d+/%d+/res')
if index and index > 0 and not file_util.exists(img_src_path) then
    local temp_img_place = string.gsub(img_place, 'product/%d+/%d+/', '')
    local temp_img_src_path =store_path_prefix .. '/' .. temp_img_place .. '/' .. img_name
    ngx_log(ngx_debug,"temp_img_src_path：",temp_img_src_path)
    if file_util.exists(temp_img_src_path) then
        img_src_path = temp_img_src_path
    end
end

--判断类似http://pic12.secooimg.com/product/${width}/${hei}/12/11/11111.jpg 格式的图片  对应的尺寸如果不存在，则寻找原图
local index= string.find(img_place,'product/%d+/%d+/')
if index and index > 0 and not file_util.exists(img_src_path) then
    local temp_img_place = string.gsub(img_place, 'product/%d+/%d+/', 'product/yt/')
    local temp_img_src_path =store_path_prefix .. '/' .. temp_img_place .. '/' .. img_name
    ngx_log(ngx_debug,"temp_img_src_path：",temp_img_src_path)
    if file_util.exists(temp_img_src_path) then
        img_src_path = temp_img_src_path
    end
end



-- 判断需要切割的图片是否存在
if not file_util.exists(img_src_path) then
    return ngx_exit(404)
end
ngx_log(ngx_debug,"img_src_path：",img_src_path)
-- 继续访问文件
return ngx_exec("@dynamic_process")