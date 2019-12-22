
import React, {Component} from 'react';
import {MapTo} from '@adobe/cq-react-editable-components';
import {Link} from "react-router-dom";

require('./image-tile.css');

class ImageTile extends Component {
    render() {
    	console.log(this.props);
        // Generates the navigation links of the application
        return (
           <div className='image-tail-container'> 
	           <Link className="nav-item" to={ this.props.tileLinkURL+'.html' }>
					<div className="tile-title">{this.props.tileTitle}</div>
					<div className="tile-image"><img src={this.props.fileReference}/></div>
				</Link>
		   </div>
        );
    }
}

export default MapTo("spaexamples/components/image-tile")(ImageTile);
