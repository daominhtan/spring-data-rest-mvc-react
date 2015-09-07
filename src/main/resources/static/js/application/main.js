var Navbar = React.createClass({
    render: function() {
        return (
            <nav className="navbar navbar-inverse navbar-fixed-top">
                <div className="container">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span className="sr-only">Toggle navigation</span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                        </button>
                        <a className="navbar-brand" href="#">Hello World!</a>
                    </div>
                    <div id="navbar" className="navbar-collapse collapse">
                        <ul className="nav navbar-nav">
                            <li className="active"><a href="/#/about">About</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        );
    }
});

var Footer = React.createClass({
    render: function() {
        return (
            <footer>
                <div className="row">
                    <div className="col-md-6 text-left">
                        <p>&copy; Nobody 2015</p>
                    </div>
                    <div className="col-md-6 text-right">
                        <a href="/#/terms">Terms of Service</a> | <a href="/#/privacy">Privacy Policy</a>
                    </div>
                </div>
            </footer>
        );
    }
});

// The RouteHandler element in this component is what handles all of our routing, nestling our content within the
// overall screen layout.
var Layout = React.createClass({
    render: function() {
        return (
            <div id="main">
                <Navbar/>
                <RouteHandler/>
                <div className="container">
                    <hr/>
                    <Footer/>
                </div>
            </div>
        );
    }
});

var About = React.createClass({
    render: function() {
        return (
            <div className="container">
                <h1>About</h1>
            </div>
        );
    }
});

var TermsOfService = React.createClass({
    render: function() {
        return (
            <div className="container">
                <h1>Terms of Service</h1>
            </div>
        );
    }
});

var PrivacyPolicy = React.createClass({
    render: function() {
        return (
            <div className="container">
                <h1>Privacy Policy</h1>
            </div>
        );
    }
});

// This is just react-ified jumbotron bootstrap example.
// http://getbootstrap.com/examples/jumbotron/
var Home = React.createClass({
    render: function() {
        return (
            <div>
                <div className="jumbotron">
                    <div className="container">
                        <h1>Hello World!</h1>
                        <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
                        <p><a className="btn btn-primary btn-lg" href="/#/about" role="button">Learn more &raquo;</a></p>
                    </div>
                </div>
                <div className="container">
                     <div className="row">
                         <div className="col-md-4">
                             <h2>Heading</h2>
                             <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                             <p><a className="btn btn-default" href="#" role="button">View details &raquo;</a></p>
                         </div>
                         <div className="col-md-4">
                             <h2>Heading</h2>
                             <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                             <p><a className="btn btn-default" href="#" role="button">View details &raquo;</a></p>
                         </div>
                         <div className="col-md-4">
                             <h2>Heading</h2>
                             <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
                             <p><a className="btn btn-default" href="#" role="button">View details &raquo;</a></p>
                         </div>
                     </div>
                </div>
            </div>
        );
    }
});

// ---------------------------------------------------------------------------------------------------------------------
// Error Handling
// ---------------------------------------------------------------------------------------------------------------------

// Consistent error handling. The 404 handling is pretty key.
var ErrorHandler = {
    _handleError: function(url, xhr, status, err, callback, defaultValue) {
        if(404 == xhr.status) {
            if(callback) {
                callback(defaultValue || null);
            }
        } else {
            console.error(url, status, err.toString());
        }
    },
};

// ---------------------------------------------------------------------------------------------------------------------
// Authentication
// ---------------------------------------------------------------------------------------------------------------------

// FIXME This is what I really want to work on to get the modal login/logout/etc. working smoothly.

var Login = React.createClass({
    render: function() {
        return (
            <div className="container">
                <h1>Login</h1>
                <form className="">
                    <div className="form-group">
                        <input type="text" placeholder="Email" className="form-control"/> <input
                            type="password" placeholder="Password" className="form-control"/> <button
                            type="submit" className="btn btn-success">Sign in</button>
                    </div>
                </form>

            </div>
        );
    }
});

// ---------------------------------------------------------------------------------------------------------------------
// Repositories
// ---------------------------------------------------------------------------------------------------------------------

// NOTE: Notice the use of PATCH for updates in order to enable partial updates.
//  SEE: http://stackoverflow.com/questions/17860520/spring-mvc-patch-method-partial-updates

// NOTE: Notice in PossessionRepository the way in which the reference to the parent is made in order to create a
//       Possession for a particular Person.

// NOTE: Notice the use of JSON.stringify(thing) to encode the javascript object instance.

// NOTE: Notice the use of X-XSRF-TOKEN header.

// NOTE: Notice the Content-Type and Accept headers required by Spring for writes.

// NOTE: Notice the use of "var self = this". This technique is popular among Javscript developers. If you're not
//       familiar with it, it's necessary to keep scoping correct in lots of situations.

var PersonRepository = {
    _itemUrl: function(id) {
        return this._collectionUrl()+'/'+id;
    },
    _collectionUrl: function() {
        return '/api/people';
    },
    get: function(id, callback) {
        var _url = this._itemUrl(id);
        $.ajax({
            url: _url,
            dataType: 'json',
            cache: false,
            success: function(data) {
                if(callback) {
                    callback(data);
                }
            }.bind(this),
                error: function(xhr, status, err) {
                    ErrorHandler._handleError(_url, xhr, status, err, callback, null);
            }.bind(this)
        });
    },
    getAll: function(callback) {
        var _url = this._collectionUrl();
        $.ajax({
            url: _url,
            dataType: 'json',
            cache: false,
            success: function(data) {
                if(callback) {
                    callback(data);
                }
            }.bind(this),
                error: function(xhr, status, err) {
                    ErrorHandler._handleError(_url, xhr, status, err, callback, []);
            }.bind(this)
        });
    },
    // Remember that custom finder we put into our PersonRepository (in the java code)? Here's how we call it.
    findByDateOfBirth: function(dateOfBirth, callback) {
        var _url = this._collectionUrl()+'/search/findByDateOfBirth?dateOfBirth='+dateOfBirth;
        $.ajax({
            url: _url,
            dataType: 'json',
            cache: false,
            success: function(data) {
                if(callback) {
                    callback(data);
                }
            }.bind(this),
                error: function(xhr, status, err) {
                    ErrorHandler._handleError(_url, xhr, status, err, callback, []);
            }.bind(this)
        });
    },
    update: function(person, callback) {
        var _url = this._itemUrl(person.id);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
            },
            url: _url,
            dataType: 'json',
            type: 'PUT',
            data: JSON.stringify(person),
            success: function(data) {
                if(callback) {
                    callback(data);
                }
            }.bind(this),
                error: function(xhr, status, err) {
                    ErrorHandler._handleError(_url, xhr, status, err, callback, null);
            }.bind(this)
        });
    },
    create: function(person, callback) {
        var _url = this._collectionUrl();
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
            },
            url: _url,
            dataType: 'json',
            type: 'POST',
            data: JSON.stringify(person),
            success: function(data) {
                callback(data);
            }.bind(this),
                error: function(xhr, status, err) {
                    ErrorHandler._handleError(_url, xhr, status, err, callback, null);
            }.bind(this)
        });
    }
};

var PossessionRepository = {
    _collectionUrl: function(id) {
        return PersonRepository._collectionUrl()+'/'+id+'/possessions';
    },
    _itemUrl: function(id) {
        return '/api/possessions/'+id;
    },
    get: function(id, callback) {
        var _url = this._itemUrl(id);
        $.ajax({
            url: _url,
            dataType: 'json',
            cache: false,
            success: function(data) {
                callback(data);
            }.bind(this),
                error: function(xhr, status, err) {
                    ErrorHandler._handleError(_url, xhr, status, err, callback, null);
            }.bind(this)
        });
    },
    getAllByPersonId: function(personId, callback) {
        var _url = this._collectionUrl(personId);
        $.ajax({
            url: _url,
            dataType: 'json',
            cache: false,
            success: function(data) {
                callback(data);
            }.bind(this),
                error: function(xhr, status, err) {
                    ErrorHandler._handleError(_url, xhr, status, err, callback, null);
            }.bind(this)
        });
    },
    update: function(possession, callback) {
        var _url = this._itemUrl(possession.id);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
            },
            url: _url,
            dataType: 'json',
            type: 'PATCH', // NOTE: Using PATCH allows partial updates. PUT would do a full update.
            data: JSON.stringify(possession),
            success: function(data) {
                callback(data);
            }.bind(this),
                error: function(xhr, status, err) {
                    ErrorHandler._handleError(_url, xhr, status, err, callback, null);
            }.bind(this)
        });
    },
    updateAll: function(personId, possessions, callback) {
        var _url = this._collectionUrl(personId);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
            },
            url: _url,
            dataType: 'json',
            type: 'PATCH', // NOTE: Using PATCH allows partial updates. PUT would do a full update.
            data: JSON.stringify(possessions),
            success: function(data) {
                callback(data);
            }.bind(this),
                error: function(xhr, status, err) {
                    ErrorHandler._handleError(_url, xhr, status, err, callback, []);
            }.bind(this)
        });
    },
    create: function(possession, person, callback) {
        var _url = '/api/possessions'; // Have to POST to the root collection.

        // NOTE: This is important. This is how we make the reference to the parent.
        //  SEE: http://stackoverflow.com/a/26426909/1174250
        possession.person = person._links.self.href;

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
            },
            url: _url,
            dataType: 'json',
            type: 'POST',
            data: JSON.stringify(possession),
            success: function(data) {
                callback(data);
            }.bind(this),
                error: function(xhr, status, err) {
                    ErrorHandler._handleError(_url, xhr, status, err, callback, null);
            }.bind(this)
        });
    },
    remove: function(id, callback) {
        var _url = this._itemUrl(id);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
            },
            url: _url,
            type: 'DELETE',
            success: function(data) {
                callback(data);
            }.bind(this),
                error: function(xhr, status, err) {
                    ErrorHandler._handleError(_url, xhr, status, err, callback, null);
            }.bind(this)
        });
    }
};

// ---------------------------------------------------------------------------------------------------------------------
// VIEW COMPONENTS
// ---------------------------------------------------------------------------------------------------------------------

var PersonController = React.createClass({
    getInitialState: function() {
        return {};
    },
    render: function() {
        var self = this;
        return (
            <div className="container">
                <p>I haven't written a real UI for the resources on the backend. It would be great to have you
                    contribute something.</p>
                <p>You'll want to look at the source code to see everything that's interesting to see.</p>
            </div>
        );
    }
});

var Router = ReactRouter;
var DefaultRoute = Router.DefaultRoute;
var Link = Router.Link;
var Route = Router.Route;
var RouteHandler = Router.RouteHandler;

// Here we define our routes.
// The interesting part about these routes is how we setup routes for the Person resource.
// The best I was able to find was to set up two separate routes, one for the collection and one for the individual item.
var routes = (
    // The Layout handler specified in the base route is inherited down the chain. This is important.
    <Route name="root" handler={Layout} path="/">
        <Route handler={Login} path="login"/>
        <Route handler={About} path="about"/>
        <Route handler={TermsOfService} path="terms"/>
        <Route handler={PrivacyPolicy} path="privacy"/>
        <Route handler={PersonController} path="people"/>
        <Route handler={PersonController} path="people/:id"/>
        <DefaultRoute handler={Home} />
    </Route>
);

// Here we write the view to the browser.
Router.run(routes, function (Handler) {
  React.render(<Handler/>, document.body);
});
