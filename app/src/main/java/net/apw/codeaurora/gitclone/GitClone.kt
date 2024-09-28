package net.apw.codeaurora.gitclone;

import android.os.Environment
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.GitAPIException
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider
import java.io.File
import kotlinx.coroutines.*

fun cloneRepositoryToDownloads(repoUrl: String) {
    CoroutineScope(Dispatchers.IO).launch{
        try {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val localDir = File(downloadsDir, "ClonedRepo")
            val git = Git.cloneRepository()
                .setURI(repoUrl)
                .setDirectory(localDir)
                .call()
            println("Repository cloned to ${git.repository.directory}")
            git.close()
        } catch (e: GitAPIException) {
            e.printStackTrace()
            println("Error cloning repository: ${e.message}")
        }
    }
}