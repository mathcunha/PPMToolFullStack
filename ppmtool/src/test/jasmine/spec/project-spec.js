var request = require("request");

var base_url = "http://localhost:8090/api/project";

let emptyProject = {
  name: "",
  identifier: "",
  description: ""
};

let Project = {
  name: "Jasmine Test",
  identifier: "JASM",
  description: "Describing Jasmine Test"
};

let completeProject = {
  id: 2,
  name: "Jasmine Test",
  identifier: "TEST",
  description: "Describing Jasmine Test",
  startDate: null,
  endDate: null,
  createdDate: "2019-10-11T16:32:05.000+0000",
  updatedDate: "2019-10-11T16:32:05.000+0000",
  version: 0
};

describe("Projects API", function() {
  describe("Create Project", function() {
    it("body should not be empty or null", function(done) {
      request.post(
        {
          uri: base_url,
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(emptyProject)
        },
        function(error, response, body) {
          expect(response.statusCode).toBe(400);
          expect(body).toMatch("required");
          done();
        }
      );
    });

    it("identifier should not be empty", function(done) {
      let data = Object.assign({}, Project, { identifier: null });
      request.post(
        {
          uri: base_url,
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(data)
        },
        function(error, response, body) {
          expect(response.statusCode).toBe(400);
          expect(body).toMatch("identifier");
          done();
        }
      );
    });

    it("identifier must use 4 to 5 characters", function(done) {
      let data = Object.assign({}, Project, { identifier: "JASMINE" });

      request.post(
        {
          uri: base_url,
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(data)
        },
        function(error, response, body) {
          expect(response.statusCode).toBe(400);
          expect(body).toMatch("identifier");
          done();
        }
      );
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
        const projectRows = projects.map(projectRow => {
          expect(
            JSON.stringify(Object.keys(completeProject).sort()) ===
              JSON.stringify(Object.keys(projectRow).sort())
          ).toBeTruthy();
        });
        done();
      });
    });
  });
});
