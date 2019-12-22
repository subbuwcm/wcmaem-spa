
package subbu.blogspot.wcmaem.spa.internal.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, adapters = {ComponentExporter.class}, resourceType = BlogsList.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class BlogsList implements ComponentExporter {

    static final String RESOURCE_TYPE = "spaexamples/components/blogs-list";
   private static final Logger LOGGER = LoggerFactory.getLogger(BlogPage.class);
	@ValueMapValue @Optional @Default(values = "/content/spaexamples/en/home")
    private String blogsRootPage;
	@SlingObject
	Resource resource;

	@SlingObject
	private ResourceResolver resourceResolver;


    List<BlogPage> items = new ArrayList<>();
 
    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }

    @PostConstruct
	public void init(){
		Page rootPage = resourceResolver.adaptTo(PageManager.class).getPage(blogsRootPage);
		if( rootPage != null ){
			Iterator<Page> pIter = rootPage.listChildren();
			while( pIter.hasNext() ){
				Page childPage = pIter.next();
				if( childPage != null && childPage.isValid()){
					String resType = childPage.getProperties().get("sling:resourceType",String.class);
					LOGGER.info("child page -=-=-"+childPage.getPath() + "Rews type -=-=-"+resType);
					if( "spaexamples/components/blog-page".equalsIgnoreCase(resType)){
						items.add(childPage.getContentResource().adaptTo(BlogPage.class));
					}
				}
			}
		}
	}

	public String getBlogsRootPage() {
		return blogsRootPage;
	}

	public List<BlogPage> getItems() {
		return items;
	}
}
