module.exports = {
  apps: [
    {
      name: "menu-service",
      script: "java",
      args: "-Dspring.profiles.active=gcp -Dspring.main.allow-bean-definition-overriding=true -Dspring.config.import=optional:configserver:http://10.0.1.10:8888 -jar menu-service-0.0.1-SNAPSHOT.jar",
      instances: 1,
      autorestart: true,
      watch: false,
      max_memory_restart: "500M",
      error_file: "./logs/menu-service-error.log",
      out_file: "./logs/menu-service-out.log",
      log_date_format: "YYYY-MM-DD HH:mm:ss",
      merge_logs: true
    },
    {
      name: "order-service",
      script: "java",
      args: "-Dspring.profiles.active=gcp -Dspring.main.allow-bean-definition-overriding=true -Dspring.config.import=optional:configserver:http://10.0.1.10:8888 -jar order-service-0.0.1-SNAPSHOT.jar",
      instances: 1,
      autorestart: true,
      watch: false,
      max_memory_restart: "500M",
      error_file: "./logs/order-service-error.log",
      out_file: "./logs/order-service-out.log",
      log_date_format: "YYYY-MM-DD HH:mm:ss",
      merge_logs: true
    },
    {
      name: "delivery-service",
      script: "java",
      args: "-Dspring.profiles.active=gcp -Dspring.main.allow-bean-definition-overriding=true -Dspring.config.import=optional:configserver:http://10.0.1.10:8888 -jar delivery-service-0.0.1-SNAPSHOT.jar",
      instances: 1,
      autorestart: true,
      watch: false,
      max_memory_restart: "500M",
      error_file: "./logs/delivery-service-error.log",
      out_file: "./logs/delivery-service-out.log",
      log_date_format: "YYYY-MM-DD HH:mm:ss",
      merge_logs: true
    },
    {
      name: "payment-service",
      script: "java",
      args: "-Dspring.profiles.active=gcp -Dspring.main.allow-bean-definition-overriding=true -Dspring.config.import=optional:configserver:http://10.0.1.10:8888 -jar payment-service-0.0.1-SNAPSHOT.jar",
      instances: 1,
      autorestart: true,
      watch: false,
      max_memory_restart: "500M",
      error_file: "./logs/payment-service-error.log",
      out_file: "./logs/payment-service-out.log",
      log_date_format: "YYYY-MM-DD HH:mm:ss",
      merge_logs: true
    }
  ]
};
