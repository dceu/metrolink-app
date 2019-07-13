package metrolink.dao;

import java.util.List;
import metrolink.entity.Stop;

public interface MetrolinkDao {
    public List<Stop> getStopsAllStops();
    public List<Stop> getStopsMetroLinkStops();
}