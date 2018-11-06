package wang.ismy.oa.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wang.ismy.oa.BaseTest;
import wang.ismy.oa.dto.CheckingTimeDto;

import static org.junit.Assert.*;

public class CheckingServiceTest extends BaseTest {

    @Autowired
    private CheckingService checkingService;

    @Test
    public void getCheckingTime() {

        CheckingTimeDto dto=checkingService.getCheckingTime();

        System.err.println(dto);
    }
}