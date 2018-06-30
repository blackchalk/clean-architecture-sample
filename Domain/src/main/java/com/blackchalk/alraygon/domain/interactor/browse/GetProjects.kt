package com.blackchalk.alraygon.domain.interactor.browse

import com.blackchalk.alraygon.domain.executor.PostExecutionThread
import com.blackchalk.alraygon.domain.interactor.ObservableUseCase
import com.blackchalk.alraygon.domain.model.Project
import com.blackchalk.alraygon.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    :ObservableUseCase<List<Project>, Nothing>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }


}