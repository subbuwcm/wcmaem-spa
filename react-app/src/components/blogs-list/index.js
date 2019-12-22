
import React, {Component} from 'react';
import {MapTo} from '@adobe/cq-react-editable-components';
import {Link} from "react-router-dom";

require('./blogs-list.css');

class BLogsList extends Component {
    render() {
    	console.log("blogs list -=-=-",this.props);
        // Generates the navigation links of the application
          const blogsItems = this.props.items.map( blog =>{
               return  (
                   <div className='blog-items' key={blog.author}>
                        <div className='card'>
                            <div className='card-content'>
                                <h5 className="card-title blue-text">{blog.Author} details</h5>
                                <div>Author: {blog.author}</div>
                                <div>publishDate: {blog.publishDate}</div>
                                <div>blogTitle: {blog.blogTitle}</div>
                                <div>subTitle: {blog.subTitle}</div>
                                <div>description: {blog.description}</div>
                                <Link className="nav-item" to={ blog.pageUrl+".html"}>Read more</Link>;
                            </div>
                        </div>
                    </div>
                )

            })

        return (
           <div className='blogs-list'>
	         {blogsItems}
		   </div>
        );
    }
}

export default MapTo("spaexamples/components/blogs-list")(BLogsList);
