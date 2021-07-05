1. 应用中引入如下jar包
```
        <dependency>
            <groupId>com.d3.dpd.cloud</groupId>
            <artifactId>d3-cloud-log-starter</artifactId>
        </dependency>
```
2. 配置spring应用中的公共变量  
```
    dpd.logging.home=xxxxx #日志根目录默认为logs  
    dpd.logging.filename=xxxx #日志文件名默认为all.log
    dpd.logging.maxsize=50MB #单日志文件最大值默认100MB
    dpd.logging.history=30 #日志最大保存时间天数 默认60
    dpd.logging.organ_id=xxx  #机构标识默认0000
    dpd.logging.organ_name=xxxxx  #应用归属机构名称 默认数据平台部
```
      
