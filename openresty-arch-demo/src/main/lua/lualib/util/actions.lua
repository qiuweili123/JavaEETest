local _M = {
    _VERSION = '0.01',
    _PREFIX_ACT='imageMogr2',
}

local mt = { __index = _M }


-- 去除图片信息，原图返回
_M["00"] =function()
    return  _M._PREFIX_ACT.."/auto-orient";
end

-- 缺省操作，限定缩略图的宽度和高度的最大值分别为 Width 和 Height，进行等比缩放
_M["c0"] =function(height,width)
    return  _M._PREFIX_ACT.."/thumbnail/"..width.."x"..height;
end
-- 取最小值进行裁剪，正方形
_M["c1"] =function(height,width)
    local cut_val=height;
    if height>width then
        cut_val=width;
    end
   return  _M._PREFIX_ACT.."/crop/"..cut_val.."x"..cut_val;
end
--按照最大值进行裁剪，正方形
_M["c2"] =function(height,width)
    local cut_val=height;
    if height<width then
        cut_val=width;
    end
    return  _M._PREFIX_ACT.."/crop/"..cut_val.."x"..cut_val;
end



return _M;