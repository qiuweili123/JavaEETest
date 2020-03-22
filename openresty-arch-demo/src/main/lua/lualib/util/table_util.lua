local _M = {
    _VERSION = '0.01',
}

local mt = { __index = _M }

-- 向table末尾追加元素
function _M.apend(t, ...)
    print("type", type(t))
    for i = 1, select("#", ...) do
        local v = select(i, ...);
        table.insert(t, v);
    end
end

-- 向table末尾追加元素
function _M.contact(t, con_char)
    return table.concat(t, con_char)
end

return _M;