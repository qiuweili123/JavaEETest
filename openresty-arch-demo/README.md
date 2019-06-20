lua图片服务
====
# 1.lua-image-web：
## (1).项目说明
    图片web服务，校验，反向代理。

## (2).服务器
    192.168.50.12
    192.168.50.14
    192.168.50.19
    192.168.50.20

---

# 2.lua-image-dynamic：
## (1).项目说明
    动态切图。

## (2).服务器：
    192.168.70.66
    192.168.70.73
    192.168.57.212
    192.168.57.213

---

# 3.lua-image-service：
## (1).项目说明
    图片上传。

## (2).服务器：
    192.168.50.12
    192.168.50.14


# 相关技术
- http://www.graphicsmagick.org/GraphicsMagick.html
- https://github.com/clementfarabet/graphicsmagick

第三方项目的Image.lua被修改了，备份在bak文件夹下
- 增加了strip 清楚图片多余信息.
- 增加了autoOrient 恢复图片正确旋转方向


# 接口文档

 ##（1） 图片压缩接口 
              - 链接：http://upload.secoolocal.com:8888/image_compress?imgdir=res/test/10/57/1WLH0Pd9407e50f69c4e8f9d6c3813025f72a9.jpeg&overwrite=1&quality=80
              - 说明：imgdir 图片地址
                      overwrite 是否覆盖原图 0 覆盖  1不覆盖
                      quality  压缩质量 注 1~100  值越大对应的压缩质量好，存储相对也大
              - 返回：{
                        "msg": { "url": "imgcompress/4032/3024/cc79b2a6f8544809b86b4db52ed01137.jpeg"},
                        "code": 200,
                        "data": "压缩成功"
                      }	

 ##（2） 图片切割接口
           - 链接：upload.secoolocal.com:8888/image_crop?imgdir=res/test/54/10/1WLD8g6e3e531ec9b843b38e0ef3a07163dbd8.jpg&imgsize=100x530&imganchor=Center&imgoffset=100x100
           - 说明：imgdir 图片地址                              
                  imgsize 需要切的尺寸 格式是宽x高，宽或者高不能同时为空，宽为空则按照图片宽度切割；高度同样，宽和高同时不能大于图片对应的宽和高
                  imganchor 图片切割锚点 值{"WestNorth" 西北（左上方）,"North"北（正上方）,"EastNorth" 东北（右上方）,"West"西（左方）,"Center"（中间）,"East"东（右方）,"WestSouth"西南（左下方）,
                            "South"南（正下方）,"EastSouth"东北（右下方},不写默认是Center
                  imgoffset 图片偏移距离  格式是 X轴xY轴  X轴和Y轴不能同时为空，X轴为空则默认为0；Y轴同样，X轴和Y轴对应的坐标不能大于图片对应的宽和高
           - 返回：{
                    "msg": {
                    "url": "res/test/54/10/1WLD8g6e3e531ec9b843b38e0ef3a07163dbd8__800x800-0x135.jpg"},
                    "code": 200
                  }
		  
 ##（3） 图片尺寸动态压缩接口
           - 链接：pic12.secooimg.com/${imageurl}_!!${width}x${height}${forcecrop}_${quality}.${suffix}
           - 示例：pic12.secooimg.com/res/test/54/10/1WLD8g6e3e531ec9b843b38e0ef3a07163dbd8.jpg_!!200x200c1_80.webp
           - 说明：imageurl 图片地址 必填
                   width	图片宽度 必填， 
                   height 图片高度 必填，
                   quality 图片压缩质量 可不填 默认是85
                   suffix 转换图片格式  必填 支持jpg，jpeg，png，webp
                   forcecrop 长方形的图片 根据 forcecrop 处理 
                               c1、是先根据最小边长度切割中间图片为正方形图片再压缩  
                               c2、按照需要压缩的尺寸根据图片的长宽算出一个最佳压缩比列压缩后再生成一个正方形的图片会有白边 
                               c3、根据最长的边和需要压缩的长度算出一个比例，进行压缩生成的还是长方形 
                               c4、根据最短的边和需要压缩的长度算出一个比例，进行压缩生成的还是长方形
		  
	允许访问的尺寸	 {"0/0", "638/638", "109/73", "160/94", "180/240", "395/232", "40/40", "44/23", "58/78", "60/60", "790/464", "112/112",
                                          "800/600", "800/800", "638/420", "650/650", "200/200", "20/20", "750/750", "50/50", "65/65", "80/80", "120/120", "160/160",
                                          "240/240", "300/300", "317/317","345/385","345/460","374/374", "500/500", "690/300", "700/700","750/920"}
	接口允许尺寸的压缩和质量的压缩，若只质量的压缩则width和height 可填写为0x0
 ##（4） 图片上传接口 
              - 链接：http://upload.secoolocal.com:8888/image_compress?dir=test&originname=1&filepath=xxxx
              - 说明：dir 图片保存文件夹 必填
                     originname 是否使用原图片名称 非必填 等于1表示使用，默认不使用，仅图片名称不变
                     files 文件格式 保存在请求体中
                     filepath 图片保存路径 只有当originname=1 时才生效
              - 返回：{
                       "msg": "",
                       "code": 200,
                       "data": [
                                   {
                                       "res_id": 457540,
                                       "size": 174114,
                                       "category": "res/test",
                                       "field_name": "fileName1",
                                       "path": "res/test/53/52/1WUZ3z/12345.jpg",
                                       "w": 513,
                                       "h": 655,
                                       "ori_name": "12345.jpg",
                                       "md5": "38a8f282b09721c1ae4e731bfa237a7f"
                                   }
                              ]
                       }
  #测试
   curl -I "http://127.0.0.1/res/c2c/bc365b96a21b48b09bff5f51d737816f.jpeg_%21%21750x920c1_85.jpg"