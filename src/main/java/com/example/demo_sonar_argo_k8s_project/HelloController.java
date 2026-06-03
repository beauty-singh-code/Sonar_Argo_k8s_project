
package com.example.demo_sonar_argo_k8s_project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Spring Boot → Docker → Jenkins → SonarQube → Nexus → Helm → ArgoCD → Kubernetes → Prometheus → Grafana project";
    }
}