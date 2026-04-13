import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class YouTubeDownloader {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- YouTube Downloader ---");
        System.out.print("動画のURLを入力してください: ");
        String url = scanner.nextLine();

        System.out.print("形式を選択してください (1: MP4 / 2: MP3): ");
        String choice = scanner.nextLine();

        String mode = choice.equals("2") ? "mp3" : "mp4";
        download(url, mode);
        
        scanner.close();
    }

    public static void download(String videoUrl, String mode) {
        List<String> command = new ArrayList<>();
        command.add("D:\\YoutubeDownlooder\\yt-dlp.exe");

        if (mode.equals("mp3")) {
            command.add("-x");
            command.add("--audio-format");
            command.add("mp3");
        } else {
            command.add("-f");
            command.add("mp4");
        }

        command.add("-o");
        command.add("%(title)s.%(ext)s");
        command.add(videoUrl);

        try {
            System.out.println("処理を開始します...");
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
            System.out.println("\n完了しました！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}