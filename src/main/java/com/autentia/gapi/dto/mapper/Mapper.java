package com.autentia.gapi.dto.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper <I, O>{

    public O map(I in);

}
