var request = require("request");

var base_url = "http://localhost:8090/api/project"

let Project = {
  name:'',
  identifier:'',
  description:''
};

describe("Projects List API Exists", function() {
  describe("GET /projects", function() {
    it("returns status code 200", function(done) {
      request.post({uri:base_url, headers:{'Content-Type': 'application/json'}, body:JSON.stringify(Project)}, function(error, response, body) {
	console.error('error:', error); // Print the error if one occurred
        console.log('statusCode:', response && response.statusCode); // Print the response status code if a response was received
        console.log('body:', body); // Print the HTML for the Google homepage.
        expect(response.statusCode).toBe(400);
        done();
      });
    });
  });

  describe("GET /projects", function() {
    it("returns status code 200", function(done) {
      request.get(base_url, function(error, response, body) {
        expect(response.statusCode).toBe(200);
        done();
      });
    });

    it("API Response should be valid array of json objects 1", function(done) {
      request.get(base_url, function(error, response, body) {
        // console.log(typeof body);
        //  body = 'Hello World';
        expect(() => {
          JSON.parse(body);
        }).not.toThrow();

        done();
      });
    });

    it("API Response should be valid array of project objects 2", function(done) {
      request.get(base_url, function(error, response, body) {
        let projects = JSON.parse(body);
        const projectRows = projects.map((projectRow) => {
          expect(JSON.stringify(Object.keys(Project).sort()) === JSON.stringify(Object.keys(projectRow).sort())).toBeTruthy();
        });
        done();
      });
    });

  });
});
