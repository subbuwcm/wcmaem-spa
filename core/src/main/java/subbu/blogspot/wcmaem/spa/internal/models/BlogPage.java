/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2018 Adobe Systems Incorporated
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package subbu.blogspot.wcmaem.spa.internal.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, adapters = {ComponentExporter.class}, resourceType = BlogPage.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class BlogPage implements ComponentExporter {

    static final String RESOURCE_TYPE = "spaexamples/components/blog-page";
	private static final Logger LOGGER = LoggerFactory.getLogger(BlogPage.class);
	@ValueMapValue @Optional
    private String author;
	@SlingObject
	Resource resource;

	@SlingObject
	private ResourceResolver resourceResolver;
	@ValueMapValue @Optional
	private String publishDate;

	@ValueMapValue @Optional
	private String blogTitle;

	@ValueMapValue @Optional
	private String subTitle;

	@ValueMapValue @Optional
	private String description;
	private String pageUrl;

	@PostConstruct
	public void init() {
		Page rootPage = resourceResolver.adaptTo(PageManager.class).getContainingPage(resource);
		pageUrl = rootPage.getPath();
	}
    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }

	public String getPageUrl() {
		return pageUrl;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "BlogPage{" +
				"author='" + author + '\'' +
				", publishDate='" + publishDate + '\'' +
				", blogTitle='" + blogTitle + '\'' +
				", subTitle='" + subTitle + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
