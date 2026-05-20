package com.wealthManagement.wealthManagement.appController;

import com.wealthManagement.wealthManagement.orchestrator.factory.OrchestratorFactory;
import com.wealthManagement.wealthManagement.pojo.responseBody.AssetDetailMapper;
import com.wealthManagement.wealthManagement.pojo.responseBody.UserDetailMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final OrchestratorFactory orchestratorFactory;

    public Controller(OrchestratorFactory orchestratorFactory) {
        this.orchestratorFactory = orchestratorFactory;
    }


    @GetMapping("/health")
    public String health() {
        return "Wealth Management Service is running 🚀 let's get rich together!";
    }

    @PostMapping("/assets")
    public List<AssetDetailMapper> getAssets(@RequestParam Long userId) {

        return orchestratorFactory
                .getAssetOrchestrator()
                .getAssets(userId);
    }
    @PostMapping("/allUsers")
    public List<UserDetailMapper> getAllUsers() {

        return orchestratorFactory
                .getUserOrchestrator()
                .getAllUsers();
    }

}
