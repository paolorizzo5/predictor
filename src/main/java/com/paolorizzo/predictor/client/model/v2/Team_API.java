package com.paolorizzo.predictor.client.model.v2;

import com.paolorizzo.predictor.client.model.v2.team._links;

public class Team_API
{
    private String squadMarketValue;

    private String crestUrl;

    private String name;

    private _links _links;

    private String code;

    private String shortName;

    public String getSquadMarketValue ()
    {
        return squadMarketValue;
    }

    public void setSquadMarketValue (String squadMarketValue)
    {
        this.squadMarketValue = squadMarketValue;
    }

    public String getCrestUrl ()
    {
        return crestUrl;
    }

    public void setCrestUrl (String crestUrl)
    {
        this.crestUrl = crestUrl;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public _links get_links ()
    {
        return _links;
    }

    public void set_links (_links _links)
    {
        this._links = _links;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getShortName ()
    {
        return shortName;
    }

    public void setShortName (String shortName)
    {
        this.shortName = shortName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [squadMarketValue = "+squadMarketValue+", crestUrl = "+crestUrl+", name = "+name+", _links = "+_links+", code = "+code+", shortName = "+shortName+"]";
    }
}
