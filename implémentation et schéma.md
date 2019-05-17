- VOID CreateCity: double x, double y, int id                                            ---->  model.createCity(DisplayCity)
- VECTOR<DISPLAYCITY> getCities: model.getCities = Vector<City>                          ---->  return Vector<DisplayCity>
- VOID UpadteCity: int id, Vector<DisplayVehicle>                                        ---->  model.modifyCity(DisplayCity)
- VOID CreateRoad: int id, int lanes, boolean multi, double[][] path, int start, int end ---->  model.createRoad(DisplayRoad)
- VECTOR<DISPLAYROAD> getRoads: model.getRoads = new Vector<Road>                        ---->  return Vector<DisplayRoad>
- VOID UpdateRoad: int id, int lanes, boolean multi                                      ---->  model.modifyRoad(DisplayRoad)
- Void CreateVehicle: int id, int start, int end, String type                            ---->  model.createVehicle(DisplayVehicle)
- VECTOR<DISPLAYVEHICLE> getRoads: model.getVehicles = new Vector<Vehicle>               ---->  return Vector<DisplayVehicle>
- VOID UpdateVehicle: int id, int start, int end, String type                            ---->  model.modifyVehicle(DisplayVehicle)





* Les int start et end sont les id des villes de départ et d'arrive
