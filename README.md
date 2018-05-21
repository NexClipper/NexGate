## NexGate
NexGate is API Gateway for DC/OS using Spring Cloud(Eureka, ZUUL).

1. Environments
    * Over JAVA 8
    * Over Spring 4
    * Over Spring boot 1.5.9
    * Over Maven 3.5.2

2. Architecture



## What is Eureka?
* Eureka is Spring Cloud project for service discovery in Micro Service Architecture. If you want to use service discover by Eureka, you have to initialize Eureka Server by putting *@EnableEurekaServer* annotaion in main class of Eureka Server Project. Then put *@EnableEurekaClient* annotaion in main class of serivce that you want to use.

* Eureka has server - client Architecture for service registry. So you must have client source to register to Eureka server.  
    ![eureka_server_client](https://steemitimages.com/600x0//https://github.com/TheNexCloud/NexGate/blob/dev-mg.kim/images/standard_eureka.png?raw=true)

* By defaults, Spring Cloud supports only JAVA application, so if you want to register NON-JAVA applicatation, you need to adopt side-car.


## NexEureka

    NexEureka is the NexCloud's customed Eureka which can register service without Eureka client. In standard Erueka, you must inject dependency to registered service. But, in NexEureka, you need just NexEureka service on your server. We made functions that crawls services deployed by Marathon and register them to Eureka Server. It renews service list by 30 secs, so it is safe when error occureed from one of services instances.

1. Benefits
    * Supports service discovery by itself.
    * Supports polyglot without side-car.
    * Supports high availability.

2. Installation