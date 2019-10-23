import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ProjectFilter } from '../project-filter';
import { ProjectService } from '../project.service';
import { Project } from '../project';

@Component({
    selector: 'project',
    templateUrl: 'project-list.component.html'
})
export class ProjectListComponent {

    filter = new ProjectFilter();
    selectedProject: Project;

    get projectList(): Project[] {
        return this.projectService.projectList;
    }

    constructor(private projectService: ProjectService) {
    }

    ngOnInit() {
    }

    search(): void {
        this.projectService.load(this.filter);
    }

    select(selected: Project): void {
        this.selectedProject = selected;
    }

}
