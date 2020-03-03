# 51well-image
fastdfs实现图片上传功能

上传图片API

下载API

部署fastdfs

```bash
docker create network fastdfs
```

```yaml
# docker-compose
version: '3'
services:
  tracker:
    container_name: tracker
    image: luhuiguo/fastdfs
    command: tracker
    network_mode: host
    volumes:   
      - /var/fdfs/tracker:/var/fdfs    
  storage0:
    container_name: storage0
    image: luhuiguo/fastdfs
    command: storage
    network_mode: host
    environment:
      - TRACKER_SERVER=tracker:22122
    volumes: 
      - /var/fdfs/storage0:/var/fdfs
```

部署当前服务

