# NexGate
* NexGate is NexCloud's API Gateway made of NexEureka and NexZUUL using Spring Cloud for DC/OS. If you just install NexEureka and NexZUUL, you can access Micro Service through service name.

* Architecture  
    ![apigateway_nexgate](https://steemitimages.com/600x0//https://github.com/TheNexCloud/NexGate/blob/dev-mg.kim/images/customed_eureka_zuul.PNG?raw=true)

* Environments
    * Over JAVA 8
    * Over Spring 4
    * Over Spring boot 1.5.9
    * Over Maven 3.5.2

* Installation
    * First option is that fork this repository and build Docker images, and deploy application on DC/OS.
    * Second option is deploy JSON configuration we suggested. JSON configuration is like samples below.
        * NexEureka
            ```json
            {
                "id": "/nexeureka",
                "backoffFactor": 1.15,
                "backoffSeconds": 1,
                "container": {
                    "portMappings": [
                    {
                        "containerPort": 8770,
                        "hostPort": 8770,
                        "labels": {
                        "VIP_0": "/nexeureka:8770"
                        },
                        "protocol": "tcp",
                        "servicePort": 10020
                    }
                    ],
                    "type": "DOCKER",
                    "volumes": [],
                    "docker": {
                    "image": "nexclipper/nexrouter",
                    "forcePullImage": true,
                    "privileged": false,
                    "parameters": []
                    }
                },
                "cpus": 0.5,
                "disk": 0,
                "healthChecks": [
                    {
                    "gracePeriodSeconds": 300,
                    "intervalSeconds": 60,
                    "maxConsecutiveFailures": 3,
                    "portIndex": 0,
                    "timeoutSeconds": 20,
                    "delaySeconds": 15,
                    "protocol": "MESOS_HTTP",
                    "path": "/check"
                    }
                ],
                "instances": 1,
                "labels": {
                    "MARATHON_TASKS_ENDPOINT": "YOUR MARATHON ENDPOINT /v2/tasks",
                    "EUREKA_ENDPOINT": "http://nexrouter.marathon.mesos:8770/eureka/apps/"
                },
                "maxLaunchDelaySeconds": 3600,
                "mem": 1536,
                "gpus": 0,
                "networks": [
                    {
                    "mode": "container/bridge"
                    }
                ],
                "requirePorts": false,
                "upgradeStrategy": {
                    "maximumOverCapacity": 1,
                    "minimumHealthCapacity": 1
                },
                "killSelection": "YOUNGEST_FIRST",
                "unreachableStrategy": {
                    "inactiveAfterSeconds": 0,
                    "expungeAfterSeconds": 0
                },
                "fetch": [],
                "constraints": []
            }
            ```

        * NexZUUL


## What is NexEureka?
* NexEureka is the NexCloud's customed Eureka which can register service without Eureka client. In standard Erueka, you must inject dependency to registered service. But, in NexEureka, you need just NexEureka service on your server. We made functions that crawls services deployed by Marathon and register them to Eureka Server. It renews service list by 30 secs, so it is safe when error occured from one of services instances.

* Waht is Eureka?
    * Eureka is Spring Cloud project for service discovery in Micro Service Architecture. If you want to use service discover by Eureka, you have to initialize Eureka Server by putting *@EnableEurekaServer* annotaion in main class of Eureka Server Project. Then put *@EnableEurekaClient* annotaion in main class of serivce that you want to use.

    * Eureka has server - client Architecture for service registry. So you must have client source to register to Eureka server.  
        ![eureka_server_client](https://steemitimages.com/600x0//https://github.com/TheNexCloud/NexGate/blob/dev-mg.kim/images/standard_eureka.png?raw=true)

    * If Eureka client is registered to Eureka server, client replicates services registered to server. If the service is not edge service, it is inefficient that eureka dependency is injected to project.

    * By defaults, Spring Cloud supports only JAVA application, so if you want to register NON-JAVA applicatation, you need to adopt side-car.

* Advantages of NexEureka
    * Supports service discovery by itself.
    * Supports polyglot without side-car.
    * Supports high availability.


## What is NexZUUL?
* NexZUUL is the NexCloud's customed ZUUL. As we explained, A service initialized as an Eureka client cached service list from Eureka server. So NexZUUL is the only eureka client on Micro Service Architecture.

* What is ZUUL?
    * ZUUL is a simple gateway service or edge service. So its main goal is routing, monitoring, error management and security. Many API gateway products offers limited functions, but ZUUL is free to customize its functions for developers.

    * API gateway using Eureka and ZUUL
        ![apigateway_eureka_zuul](https://steemitimages.com/600x0//https://github.com/TheNexCloud/NexGate/blob/dev-mg.kim/images/standard_eureka_zuul.PNG?raw=true)  
        *You must inject Eureka client dependency to you micro service*

* With NexEureka, just deploy micro services with no additional dependencies and access service though service name.