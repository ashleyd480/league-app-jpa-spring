package com.example.leagueapp.aop;

import com.example.leagueapp.model.Tournament;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TournamentLoggingAspect {
//join point- method being intercepted
//proceedingjoinpont allows you to read into the method
//controller will think it's calling the service- it will hit the around method before it hits the service.

    @Pointcut("execution(* com.example.leagueapp.service.TournamentService.addTournament(..)) && args(tournament)")
    public void addTournamentExecution(Tournament tournament) {
    }

    @Around("addTournamentExecution(tournament)")
    public Tournament logAddTournament(ProceedingJoinPoint joinPoint, Tournament tournament) throws Throwable {
        // Additional logic before invoking addTournament
        log.info("Before addTournament execution");
        tournament.setName("MLSO");
        // Proceed with the original method invocation
        Tournament addedTournament = (Tournament) joinPoint.proceed();

        // Additional logic after invoking addTournament
        log.info("After addTournament execution");

        return addedTournament;
    }



}