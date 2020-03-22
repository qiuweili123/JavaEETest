local SCRIPT_ROOT_PATH = "openresty-arch-demo";

  function init()
    initLuaModule();
end

  function initLuaModule(paths)
    local rootPath = SCRIPT_ROOT_PATH .. '/image';
    package.path = string.format("%s/?.lua;%s", rootPath, package.path)
end

init();