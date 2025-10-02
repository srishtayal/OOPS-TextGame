public class Colors {
    // ANSI Color codes
    public static final String RESET = "\033[0m";
    
    // Regular colors
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";
    
    // Bold colors
    public static final String BOLD_BLACK = "\033[1;30m";
    public static final String BOLD_RED = "\033[1;31m";
    public static final String BOLD_GREEN = "\033[1;32m";
    public static final String BOLD_YELLOW = "\033[1;33m";
    public static final String BOLD_BLUE = "\033[1;34m";
    public static final String BOLD_PURPLE = "\033[1;35m";
    public static final String BOLD_CYAN = "\033[1;36m";
    public static final String BOLD_WHITE = "\033[1;37m";
    
    // Background colors
    public static final String BG_BLACK = "\033[40m";
    public static final String BG_RED = "\033[41m";
    public static final String BG_GREEN = "\033[42m";
    public static final String BG_YELLOW = "\033[43m";
    public static final String BG_BLUE = "\033[44m";
    public static final String BG_PURPLE = "\033[45m";
    public static final String BG_CYAN = "\033[46m";
    public static final String BG_WHITE = "\033[47m";
    
    // Game-specific color methods
    public static String health(String text) {
        return GREEN + text + RESET;
    }
    
    public static String lowHealth(String text) {
        return RED + text + RESET;
    }
    
    public static String criticalHealth(String text) {
        return BOLD_RED + text + RESET;
    }
    
    public static String enemy(String text) {
        return RED + text + RESET;
    }
    
    public static String bossEnemy(String text) {
        return BOLD_RED + text + RESET;
    }
    
    public static String item(String text) {
        return CYAN + text + RESET;
    }
    
    public static String weapon(String text) {
        return YELLOW + text + RESET;
    }
    
    public static String gem(String text) {
        return PURPLE + text + RESET;
    }
    
    public static String healing(String text) {
        return GREEN + text + RESET;
    }
    
    public static String room(String text) {
        return BOLD_CYAN + text + RESET;
    }
    
    public static String player(String text) {
        return BOLD_BLUE + text + RESET;
    }
    
    public static String score(String text) {
        return BOLD_YELLOW + text + RESET;
    }
    
    public static String victory(String text) {
        return BOLD_GREEN + text + RESET;
    }
    
    public static String defeat(String text) {
        return BOLD_RED + text + RESET;
    }
    
    public static String action(String text) {
        return BOLD_WHITE + text + RESET;
    }
    
    public static String warning(String text) {
        return BOLD_YELLOW + text + RESET;
    }
    
    public static String menu(String text) {
        return BLUE + text + RESET;
    }
    
    public static String success(String text) {
        return BOLD_GREEN + text + RESET;
    }
    
    public static String damage(String text) {
        return BOLD_RED + text + RESET;
    }
    
    // Helper method to get health color based on percentage
    public static String getHealthColor(int current, int max) {
        double percentage = (double) current / max;
        if (percentage > 0.6) return GREEN;
        else if (percentage > 0.3) return YELLOW;
        else return RED;
    }
    
    // Method to create a health bar
    public static String healthBar(int current, int max) {
        int barLength = 20;
        int filledLength = (int) ((double) current / max * barLength);
        
        StringBuilder bar = new StringBuilder();
        String color = getHealthColor(current, max);
        
        bar.append(color).append("[");
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append("█");
            } else {
                bar.append("░");
            }
        }
        bar.append("]").append(RESET);
        
        return bar.toString();
    }
}