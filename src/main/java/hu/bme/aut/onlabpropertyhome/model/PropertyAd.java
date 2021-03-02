package hu.bme.aut.onlabpropertyhome.model;

public class PropertyAd {
    private AdDTO adDTO;
    private PropertyDTO propertyDTO;

    public PropertyAd() { }

    public void setAdDetails(AdDTO adDTO) {
        this.adDTO = adDTO;
    }
    public void setPropertyDetails(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }

    public AdDTO getAdDetails() {
        return this.adDTO;
    }
    public PropertyDTO getPropertyDetails() {
        return this.propertyDTO;
    }
}
