import { Project } from './project';
import { ProjectFilter } from './project-filter';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable()
export class ProjectService {
    
    constructor(private http: HttpClient) {
    }

    projectList: Project[] = [];
  
    findById(id: string): Observable<Project> {
        let url = "http://localhost:8090/api/project/" + id;
        let params = { "id": id };
        let headers = new HttpHeaders()
                            .set('Accept', 'application/json');
        return this.http.get<Project>(url, {params, headers});
    }
    
    load(filter: ProjectFilter): void {
        this.find(filter).subscribe(
            result => {
                this.projectList = result;
            },
            err => {
                console.error('error loading', err);
            }
        )
    }

    find(filter: ProjectFilter): Observable<Project[]> {
        let url = 'http://localhost:8090/api/project';
        let headers = new HttpHeaders()
                            .set('Accept', 'application/json');

        let params = {
            "description": filter.description,
        };

        return this.http.get<Project[]>(url, {params, headers});
    }

    save(entity: Project): Observable<Project> {
        let url = 'http://localhost:8090/api/project';
        let headers = new HttpHeaders()
            .set('Accept', 'application/json');
        return this.http.post<Project>(url, entity, {headers});
    }
}

