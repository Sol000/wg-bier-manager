___
## Quick Guide:

### Prerequesits
- nodejs
- [bun](https://bun.sh/)
- docker + compose plugin (Docker Desktop is nice to have)
- openjdk-21
- gradle > 6.0

### Starting the application

#### On Server
    chmod +x run.sh
    ./run.sh --prod

> for hard reset (erases DB) use `./run.sh --prod --hard-reset`

#### Locally
    chmod +x run.sh
    ./run.sh 

> for hard reset (erases DB) use `./run.sh --hard-reset`

### Port-Mapping
mongodb --> `27017` \
backend ----> `8080` \
frontend ----> `5173` \
proxy --------> `80`, `443`

> If you start the application stack locally all the ports are available on `http://localhost`

> On server only port `443` is reachable, `80` is automatically redirected to `443`.


