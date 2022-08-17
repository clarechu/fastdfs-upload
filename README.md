# fastdfs-upload


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

部署nginx

```yaml
# nginx docker-compose 
version: '3'
services:
  nginx:
    container_name: nginx
    image: nginx
    network_mode: host
    volumes:
      - ./conf.d:/etc/nginx/conf.d
      - /var/fdfs/storage0/data:/var/fdfs/storage/data/group1/M00

```

##### default.conf


```text
server {
    listen       80;
    server_name  127.0.0.1;

    #charset koi8-r;
    #access_log  /var/log/nginx/host.access.log  main;

    location  /group1/M00 {
         root /var/fdfs/storage/data;
    }
    #error_page  404              /404.html;

    # redirect server error pages to the static page /50x.html
    #
    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   /usr/share/nginx/html;
    }

    # proxy the PHP scripts to Apache listening on 127.0.0.1:80
    #
    #location ~ \.php$ {
    #    proxy_pass   http://127.0.0.1;
    #}

    # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
    #
    #location ~ \.php$ {
    #    root           html;
    #    fastcgi_pass   127.0.0.1:9000;
    #    fastcgi_index  index.php;
    #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
    #    include        fastcgi_params;
    #}

    # deny access to .htaccess files, if Apache's document root
    # concurs with nginx's one
    #
    #location ~ /\.ht {
    #    deny  all;
    #}
}
```

部署当前服务

```yaml
version: '3'
services:
  image-upload:
    container_name: image-upload
    image: harbor.cloud2go.cn/cloudos-dev/image-upload:RC4.24.0
    ports:
    - 8080:8080
    environment:
    - fdfs_tracker-list=10.10.13.50:22122


```