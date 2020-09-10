package com.twu;

import com.twu.entity.HotSearch;
import com.twu.entity.User;
import com.twu.exception.RepeatedHotSearchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PersonTest {


    HotSearch hotSearch1 = new HotSearch("111");

    User user = new User("yangqian");


    @Test
    void shouldReturnSuccessWhenAddNewHotSearch() {

        user.addHotSearch(hotSearch1);

        Assertions.assertEquals(1, user.findHotSearch("111"));
    }

    @Test
    void shouldReturnExceptionWhenAddExistedHotSearch() {

        RepeatedHotSearchException exception = Assertions.assertThrows(RepeatedHotSearchException.class, () -> user.addHotSearch(hotSearch1));

        Assertions.assertEquals("该热搜已存在，请勿重复提交！", exception.getMessage());

    }

}
