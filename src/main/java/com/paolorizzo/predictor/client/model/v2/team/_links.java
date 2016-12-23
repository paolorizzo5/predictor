package com.paolorizzo.predictor.client.model.v2.team;

public class _links
{
    private Fixtures fixtures;

    private Players players;

    private Self self;

    public Fixtures getFixtures ()
    {
        return fixtures;
    }

    public void setFixtures (Fixtures fixtures)
    {
        this.fixtures = fixtures;
    }

    public Players getPlayers ()
    {
        return players;
    }

    public void setPlayers (Players players)
    {
        this.players = players;
    }

    public Self getSelf ()
    {
        return self;
    }

    public void setSelf (Self self)
    {
        this.self = self;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [fixtures = "+fixtures+", players = "+players+", self = "+self+"]";
    }
}