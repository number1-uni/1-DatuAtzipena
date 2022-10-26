package dambi.pojoak.salmenta;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "Salmentak" )
public class Salmentak {
    List<Salmenta> salmentak;

    public List<Salmenta> getSalmentak()
    {
        return salmentak;
    }

    /**
     * element that is going to be marshaled in the xml
     */
    @XmlElement( name = "Salmenta" )
    public void setSalmentak( List<Salmenta> salmentak )
    {
        this.salmentak = salmentak;
    }

    /**
     * This method is not used by jaxb, just used for business reasons. In the case that this class
     * would be generated using xml schemas definitions, this method has to be added to the
     * generated class or to some helper or util one
     * 
     * @param salmenta
     */
    public void add( Salmenta salmenta )
    {
        if( this.salmentak == null )
        {
            this.salmentak = new ArrayList<Salmenta>();
        }
        this.salmentak.add( salmenta );

    }

    @Override
    public String toString()
    {
        StringBuffer str = new StringBuffer();
        for( Salmenta salmenta : this.salmentak )
        {
            str.append( salmenta.toString());
        }
        return str.toString();
    }
}
