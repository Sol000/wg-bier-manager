___
### Prerequesits
to install all the dependencies run:
    
    python3 run.py --setup

### Starting the APP

#### On Server
    python3 run.py --prod

#### Locally (without Nginx)
    python3 run.py --local

> To reset the database service add the `--hard-reset` flag to an starting command 

### Port-Mapping in docker-env
mongodb --> `27017` \
backend ----> `8080` \
frontend ----> `5173` \
proxy --------> `80`, `443`

> If you start the application stack locally all the ports are available on `http://localhost`

> On server only port `443` is reachable, `80` is automatically redirected to `443`.


