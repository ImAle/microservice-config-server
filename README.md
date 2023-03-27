# Microservices with spring
The reason of this repository is store the config-data used by the config server git to provide configuration to microservices. Additionally, It also stores the process of such microservices creation.

## Branch: Master
This branch has working microservices ready to be part of the rest of the project.
They have been communicated using RestTemplate in some methods and Feign Client in some others.
</br>
</br>
</br>

## Branch: Config-server
(Git) Config-server has been created and configuration of such microservices is now externalized in 'config-data' folder.
</br>
</br>
</br>

## Branch: Eureka
Eureka discovery server has been created and microservices now registher there.
</br>
</br>
</br>

## Branch: multiple-instances
The microservices has been modified so that you can now deploy several of the same.
</br>
</br>
</br>

## Branch: Gateway
API Gateway server has been created. There is no longer need to keep changing ports everytime you want to interact with a different microservice. The default port now is 8080.
</br>
</br>
</br>

## Circuit-Breaker
Circuit-breaker configuration has been added to /config-data/person-service using resilience4j. This microservice will not crash if communication fails.
</br>
</br>
</br>

# tech stack
This code use java and spring to work.


