module.exports = {
  apps: [
    {
      name: "menu-service",
      script: "java",
      args: "-Dspring.profiles.active=gcp -Dspring.main.allow-bean-definition-overriding=true -Dspring.config.import=optional:configserver:http://10.0.1.10:8888 -jar menu-service-0.0.1-SNAPSHOT.jar",
      instances: 1,
      autorestart: true,
      watch: false,
      max_memory_restart: "500M"
    },
    {
      name: "order-service",
      script: "java",
      args: "-Dspring.profiles.active=gcp -Dspring.main.allow-bean-definition-overriding=true -Dspring.config.import=optional:configserver:http://10.0.1.10:8888 -jar order-service-0.0.1-SNAPSHOT.jar",
      instances: 1,
      autorestart: true,
      watch: false,
      max_memory_restart: "500M"
    },
    {
      name: "delivery-service",
      script: "java",
      args: "-Dspring.profiles.active=gcp -Dspring.main.allow-bean-definition-overriding=true -Dspring.config.import=optional:configserver:http://10.0.1.10:8888 -jar delivery-service-0.0.1-SNAPSHOT.jar",
      instances: 1,
      autorestart: true,
      watch: false,
      max_memory_restart: "500M"
    },
    {
      name: "payment-service",
      script: "java",
      args: "-Dspring.profiles.active=gcp -Dspring.main.allow-bean-definition-overriding=true -Dspring.config.import=optional:configserver:http://10.0.1.10:8888 -jar payment-service-0.0.1-SNAPSHOT.jar",
      instances: 1,
      autorestart: true,
      watch: false,
      max_memory_restart: "500M"
    }
  ]
};
