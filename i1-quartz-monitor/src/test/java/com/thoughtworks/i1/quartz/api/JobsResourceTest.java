package com.thoughtworks.i1.quartz.api;

import com.sun.jersey.api.client.ClientResponse;
import com.thoughtworks.i1.commons.test.AbstractResourceTest;
import com.thoughtworks.i1.commons.test.ApiTestRunner;
import com.thoughtworks.i1.commons.test.RunWithApplication;
import com.thoughtworks.i1.quartz.domain.JobVO;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static com.thoughtworks.i1.quartz.domain.JobVO.QuartzVOBuilder.aQuartzVO;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(ApiTestRunner.class)
@RunWithApplication(QuartzTestApplication.class)
public class JobsResourceTest  extends AbstractResourceTest {

    @Test
    public void should_return_empty_job_items() {
        ClientResponse response = get("/api/quartz-jobs/items");

        assertThat(response.getClientResponseStatus(), is(ClientResponse.Status.OK));
    }

    @Test
    public void should_invoke_url(){

//        List<TriggerVO> triggerVOList = new ArrayList<>();
//        TriggerVO triggerVO = new TriggerVO.TriggerVOBuilder(parent, triggerName, triggerGroupName).createTriggerVO();
//        triggerVO.setTriggerName("a");
//        triggerVO.setTriggerGroupName("aaa");
//        triggerVO.setTriggerState("default");
//        triggerVO.setStartTime(new Date());
//        triggerVO.setEndTime(new Date());
//        triggerVO.setRepeatCount(9);
//        triggerVO.setRepeatInterval(7);
//        triggerVOList.add(triggerVO);
//
//        List<JobDataVO> jobDataVOList = new ArrayList<>();
//        JobDataVO jobDataVO = new JobDataVO();
//        jobDataVO.setKey("url");
//        jobDataVO.setValue("http://localhost:8051/heren/api/diagnosis-clinic-dict/test");
//        jobDataVOList.add(jobDataVO);
//
//        JobVO jobVO = new JobVO.QuartzVOBuilder().createQuartzVO();
//        jobVO.setJobName("aa");
//        jobVO.setJobGroupName("herenSchedule");
//        jobVO.setDescription("use schedule");
//        jobVO.setJobClass("com.thoughtworks.i1.quartz.jobs.JobForUrl");
//        jobVO.setTriggers(triggerVOList);
//        jobVO.setJobDatas(jobDataVOList);

        JobVO jobVO = aQuartzVO().jobDetail("b", "herenSchedule", "com.thoughtworks.i1.quartz.jobs.JobForUrl")
                .addJobData("url", "http://localhost:8051/heren/api/diagnosis-clinic-dict/test").end()
                .addTrigger("a", "herenTrigger").time(new Date(), new Date()).repeat(7, 9).end()
                .build();

        ClientResponse clientResponse = get("/api/quartz-jobs/items");
        assertThat(clientResponse.getClientResponseStatus(), is(ClientResponse.Status.OK));
    }
}
