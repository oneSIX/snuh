@file:Suppress("UnstableApiUsage")

package com.kents.snuh.lintchecks

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class LintRegistry : IssueRegistry() {
    override val api: Int
        get() = CURRENT_API

    override val issues: List<Issue>
        get() = listOf(
            MissingExcludePreviewAnnotationDetector.ISSUE_MISSING_EXCLUDE_PREVIEW_ANNOTATION
        )
}
