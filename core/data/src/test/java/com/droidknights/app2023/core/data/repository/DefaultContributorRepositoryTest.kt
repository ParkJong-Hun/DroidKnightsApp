package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.data.api.fake.FakeGithubApi
import com.droidknights.app2023.core.data.api.model.ContributorResponse
import com.droidknights.app2023.core.data.model.ContributorEntity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe


internal class DefaultContributorRepositoryTest : BehaviorSpec() {

    private val repository: DefaultContributorRepository = DefaultContributorRepository(
        githubApi = FakeGithubApi(contributors)
    )

    init {
        Given("기여자가 존재한다") {
            val expected = contributors

            When("기여자를 조회한다") {
                val contributors: List<ContributorEntity> = repository.getContributors(
                    owner = "droidknights",
                    name = "app2023"
                )
                Then("기여자를 반환한다") {
                    contributors.size shouldBe 1
                    contributors.all {
                        it.name == expected[0].name
                    }
                }
            }
        }
    }

    companion object {
        private val contributors = listOf(
            ContributorResponse(
                name = "test name",
                imageUrl = "test image url"
            )
        )
    }
}