package com.wealthManagement.wealthManagement.appController;

import com.wealthManagement.wealthManagement.pojo.responseBody.AssetDetailMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.wealthManagement.wealthManagement.mybatis.mapper.AssetDetail;

@RestController
public class Controller {

    private final AssetDetail assetDetail;

    public Controller(AssetDetail assetDetail) {
        this.assetDetail = assetDetail;
    }

    @GetMapping("/health")
    public String health() {
        return "Wealth Management Service is running 🚀 let's get rich together!";
    }

    @PostMapping("/assets")
    public List<AssetDetailMapper> getAssets(@RequestParam(name = "userId") Long userId) {
        return assetDetail.selectAssetsByUserId(userId);
    }
}
