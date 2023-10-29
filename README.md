___
## Quick Guide:

### Prerequesits
- nodejs
- [bun](https://bun.sh/)
- docker + compose plugin (Docker Desktop is nice to have)
- openjdk-21
- gradle > 6.0

### Starting the application

    chmod +x run.sh
    ./run.sh

### Port-Mapping
mongodb --> `27017` \
backend ----> `8080` \
frontend ----> `5173` \
proxy --------> `80`

> If you start the application stack locally all the ports are available on `http://localhost`

> On server only port `80` is reachable


