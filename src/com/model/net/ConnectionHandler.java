package com.model.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.model.game.character.player.Player;
import com.model.utility.FileUtils;



/**
 * The Connection Check Class
 * 
 * @author Ryan
 * @author Lmctruck30 Revision by Shawn Notes by Shawn
 */
public class ConnectionHandler {

	/**
	 * Bans & mutes.
	 */
	public static Set<String> bannedIps = new HashSet<>(), macBannedUsers = new HashSet<>(), mutedIps = new HashSet<>(),
			mutedNames = new HashSet<>(), loginLimitExceeded = new HashSet<>(), starterRecieved1 = new HashSet<>(),
			starterRecieved2 = new HashSet<>(), lockedAccounts = new HashSet<>();
	public static Collection<String> bannedIds = new ArrayList<String>();
	public static ArrayList <String> bannedNames = new ArrayList<String> ();
	private static final File IDENTITY_BANS = new File(FileUtils.getSanctionDirectory(), "identitybanned.txt");

	public static void initialize() {
		banUsers();
		macBannedUsers();
		banIps();
		muteUsers();
		muteIps();
		lockAccounts();
	}

	public static void addIpToStarter1(String IP) {
		starterRecieved1.add(IP);
		addIpToStarterList1(IP);
	}

	public static void addIpToStarter2(String IP) {
		starterRecieved2.add(IP);
		addIpToStarterList2(IP);
	}

	public static void addIpToStarterList1(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("./Data/starters/FirstStarterRecieved.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addIpToStarterList2(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("./Data/starters/SecondStarterRecieved.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean hasRecieved1stStarter(String IP) {
		if (starterRecieved1.contains(IP)) {
			return true;
		}
		return false;
	}

	public static boolean hasRecieved2ndStarter(String IP) {
		if (starterRecieved2.contains(IP)) {
			return true;
		}
		return false;
	}

	/**
	 * Adding names to the list.
	 */
	public static void addIpToLoginList(String IP) {
		loginLimitExceeded.add(IP);
	}

	/**
	 * Removes IPs from the list.
	 */
	public static void removeIpFromLoginList(String IP) {
		loginLimitExceeded.remove(IP);
	}

	/**
	 * Clears the name from the list.
	 */
	public static void clearLoginList() {
		loginLimitExceeded.clear();
	}

	/**
	 * Checks the list for blocked IPs if a user is trying to login.
	 */
	public static boolean checkLoginList(String IP) {
		loginLimitExceeded.add(IP);
		int num = 0;
		for (String ips : loginLimitExceeded) {
			if (IP.equals(ips)) {
				num++;
			}
		}
		if (num > 5) {
			return true;
		}
		return false;
	}

	/**
	 * Removes a muted user from the muted list.
	 */
	public static void unMuteUser(String name) {
		mutedNames.remove(name);
		deleteFromFile("./Data/bans/UsersMuted.txt", name);
	}
	
	/**
	 * Unlocks the player's account.
	 * @param name
	 */
	public static void unlockAccount(String name) {
		lockedAccounts.remove(name);
		deleteFromFile("./Data/bans/LockedAccounts.txt", name);
	}

	/**
	 * Removes a banned user from the banned list.
	 **/
	public static void removeNameFromBanList(String name) {
		bannedNames.remove(name.toLowerCase());
		deleteFromFile("./Data/bans/UsersBanned.txt", name);
	}

	public static void removeNameFromMuteList(String name) {
		bannedNames.remove(name.toLowerCase());
		deleteFromFile("./Data/bans/UsersMuted.txt", name);
	}

	/**
	 * Void needed to delete users from a file.
	 */
	public synchronized static void deleteFromFile(String file, String name) {
		try {
			BufferedReader r = new BufferedReader(new FileReader(file));
			ArrayList<String> contents = new ArrayList<>();
			while (true) {
				String line = r.readLine();
				if (line == null) {
					break;
				}
				line = line.trim();
				String args[] = line.split("-");
				if (!args[0].equalsIgnoreCase(name) && !line.equals("")) {
					contents.add(line);
				}
			}
			r.close();
			BufferedWriter w = new BufferedWriter(new FileWriter(file));
			for (String line : contents) {
				w.write(line, 0, line.length());
				w.newLine();
			}
			w.flush();
			w.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Removes an IP address from the IPmuted list.
	 */
	public static void unIPMuteUser(String name) {
		mutedIps.remove(name);
		deleteFromFile("./Data/bans/IpsMuted.txt", name);
	}

	/**
	 * Removes an IP address from the IPBanned list.
	 **/
	public static void removeIpFromBanList(String IP) {
		bannedIps.remove(IP);
	}

	/**
	 * Adds a user to the banned list.
	 **/
	public static void addNameToBanList(String name) {
		bannedNames.add(name.toLowerCase());
	}

	public static void addNameToMuteList(String name) {
		mutedNames.add(name.toLowerCase());
		addUserToFile(name);
	}
	
	public static void lockAccount(String name) {
		lockedAccounts.add(name.toLowerCase());
		addNameToLocks(name);
	}

	/**
	 * Adds a user to the muted list.
	 */
	public static void muteUsers() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("./Data/bans/UsersMuted.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					mutedNames.add(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds an IP address to the IPMuted list.
	 */
	public static void muteIps() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("./Data/bans/IpsMuted.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					mutedIps.add(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Populates the Locked Accounts list with names from the <code>HashSet</code>.
	 */
	public static void lockAccounts() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("./Data/bans/LockedAccounts.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					lockedAccounts.add(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds an IP address to the IPBanned list.
	 **/
	public static void addIpToBanList(String IP) {
		bannedIps.add(IP);
	}

	/**
	 * Adds an IP address to the IPMuted list.
	 */
	public static void addIpToMuteList(String IP) {
		mutedIps.add(IP);
		addIpToMuteFile(IP);
	}

	/**
	 * Contains banned IP addresses.
	 **/
	public static boolean isIpBanned(String IP) {
		if (bannedIps.contains(IP)) {
			return true;
		}
		return false;
	}

	/**
	 * Contains banned users.
	 **/
	public static boolean isNamedBanned(String name) {
		if(bannedNames.contains(name.toLowerCase())) {
			return true;
		}
		return false;
	}
	
	public static void bannedIds() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(IDENTITY_BANS));
			String data;
			try {
				while ((data = in.readLine()) != null) {
					ConnectionHandler.addIdentityToList(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets whether the specified player name has been locked.
	 * @param name	the name to check the <code>Collection</code> for.
	 * @return	true if the user is locked, false if not, or if a null pointer exception is thrown
	 */
	public static boolean isLocked(String name) {
		try {
			return lockedAccounts.contains(name.toLowerCase());
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * Reads all users from text file then adds them all to the ban list.
	 **/
	public static void banUsers() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("./Data/bans/UsersBanned.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					addNameToBanList(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void macBannedUsers() {
		File file = new File("./Data/bans/MacBanned.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			return;
		}
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String line = null;
			while ((line = in.readLine()) != null) {
				if (!macBannedUsers.contains(line) && !line.isEmpty()) {
					macBannedUsers.add(line);
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized void addMacBan(String address) {
		if (!macBannedUsers.contains(address) && !address.isEmpty()) {
			macBannedUsers.add(address);
			updateMacBanFile();
		}
	}

	public static synchronized void removeMacBan(String address) {
		if (macBannedUsers.contains(address) && !address.isEmpty()) {
			macBannedUsers.remove(address);
			updateMacBanFile();
		}
	}

	private static synchronized void updateMacBanFile() {
		try (BufferedWriter out = new BufferedWriter(new FileWriter("./Data/bans/MacBanned.txt"))) {
			for (String mac : macBannedUsers) {
				out.write(mac);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads all the IPs from text file then adds them all to ban list.
	 **/
	public static void banIps() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("./Data/bans/IpsBanned.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					addIpToBanList(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeIpBan(String remove) throws IOException {
		List<String> data = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader("./Data/bans/IpsBanned.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				data.add(line);
			}
		}
		data.removeIf(s -> s.equals(remove));
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("./Data/bans/IpsBanned.txt"))) {
			for (String line : data) {
				writer.write(line);
				writer.newLine();
			}
		}
	}

	/**
	 * Reload the list of banned ips.
	 */
	public static void resetIpBans() {
		bannedIps = new HashSet<>();
		banIps();
	}
	
	/**
	 * Adds the specified string literal to the list of locked accounts.
	 * @param name
	 * @throws IOException
	 */
	public static void addNameToLocks(String name) {
		deleteFromFile("./Data/bans/LockedAccounts.txt", name.toLowerCase());
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("./Data/bans/LockedAccounts.txt", true));
			try {
				out.newLine();
				out.write(name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes the user into the text file when using the ::ban command.
	 **/
	public static void addNameToFile(String name) {
		try {
		BufferedWriter out = new BufferedWriter(new FileWriter("./Data/bans/UsersBanned.txt", true));
		try {
			out.newLine();
			out.write(name);
	    } finally {
			out.close();
	    }
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	/**
	 * Writes the user into the text file when using the ::mute command.
	 */
	public static void addUserToFile(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("./Data/bans/UsersMuted.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes the IP into the text file when using the ::ipban command.
	 **/
	public static void addIpToFile(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("./Data/bans/IpsBanned.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes the IP into the text file when using the ::mute command.
	 */
	public static void addIpToMuteFile(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("./Data/bans/IpsMuted.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addIdentityToFile(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(IDENTITY_BANS, true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Needed boolean for muting.
	 */
	public static boolean isMuted(Player c) {
		// return mutedNames.contains(c.playerName) ||
		// mutedIps.contains(c.connectedFrom);
		return false;
	}

	public static boolean isMacBanned(String address) {
		return macBannedUsers.contains(address);
	}

	public static boolean isIdBanned(String id) {
		return bannedIds.contains(id);
	}
	
	public static void addIdentityToList(String IP) {
		bannedIds.add(IP);
	}
	
	public static void removeIdentityBan(String identity) {
		bannedIds.remove(identity);
		deleteFromFile(IDENTITY_BANS, identity);
	}
	
	public static void deleteFromFile(File file, String name) {
		try {
			BufferedReader r = new BufferedReader(new FileReader(file));
			ArrayList<String> contents = new ArrayList<String>();
			while (true) {
				String line = r.readLine();
				if (line == null) {
					break;
				} else {
					line = line.trim();
				}
				if (!line.equalsIgnoreCase(name)) {
					contents.add(line);
				}
			}
			r.close();
			BufferedWriter w = new BufferedWriter(new FileWriter(file));
			for (String line : contents) {
				w.write(line, 0, line.length());
				w.newLine();
			}
			w.flush();
			w.close();
		} catch (Exception ignored) {
			ignored.printStackTrace();
		}
	}


}