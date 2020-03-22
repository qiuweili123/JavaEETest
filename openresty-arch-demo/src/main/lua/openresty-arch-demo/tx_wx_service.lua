local action_util = require 'util.actions'
local table_util = require 'util.table_util'
local json = require "cjson"


local ngx_capture = ngx.location.capture
local ngx_log = ngx.log
local ngx_debug = ngx.DEBUG
local ngx_var = ngx.var
local ngx_exec = ngx.exec
local ngx_exit = ngx.exit

local img_width = tonumber(ngx_var.img_width);
local img_height = tonumber(ngx_var.img_height);
local img_quality = tonumber(ngx_var.img_quality);
local img_old_suffix = ngx_var.img_old_suffix;
local img_suffix = ngx_var.img_suffix;
local img_act = ngx_var.img_act; --处理图片动作

local path = '/' .. ngx_var.img_place .. "/" .. ngx_var.img_name;
local t_uri_args = {}; --图片处理参数

if not img_quality then
    img_quality = 85
end
-- 需要原图处理
if img_width == 0 and img_height == 0 then
    img_act = "00";
end

if not img_act or img_act == "" then
    img_act = "c0";
end

ngx_log(ngx_debug, "path##", path, "img_width:", img_width, "img_height:", img_height, "img_quality:", img_quality, "img_act", img_act, "img_old_suffix", img_old_suffix, "img_suffix", img_suffix);

-- 获取不同动作的处理方式
local arg_action = action_util[img_act](img_height, img_width,path);

-- c5复合处理图片形式
-- 开始
if img_act == 'c5' then
    --获取原图片信息

end
--结束

--拼接处理
table_util.apend(t_uri_args, arg_action, "q", img_quality)

if img_old_suffix ~= img_suffix then
    table_util.apend(t_uri_args, "format", img_suffix)
end

local uri_args = table_util.contact(t_uri_args, "/");

ngx_log(ngx_debug, "path::", path, " uri_args::", uri_args);

ngx.req.set_uri_args(uri_args)
ngx.req.set_uri(path);