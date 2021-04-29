package fr.univrouen.cv21rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cvlist")
@XmlAccessorType(XmlAccessType.FIELD)
public class CVsResume {
    @XmlElement(name = "cv" )
    private List<CVResume> cvList = new ArrayList<CVResume>();

    public CVsResume() {
    }
    public CVsResume(List<CV> cvList) {
        for (CV cvi : cvList){
            CVResume resumeCV = new CVResume();
            resumeCV.setId(cvi.getId());
            resumeCV.setIdentite(cvi.getIdentite());
            resumeCV.setObjectif(cvi.getObjectif());
            this.cvList.add(resumeCV);
        }
    }

    public List<CVResume> getCVSList() {
        return cvList;
    }
}
